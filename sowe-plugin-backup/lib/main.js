var Request = require('sdk/request').Request;
var widgets = require('sdk/widget');
var data = require('sdk/self').data;
var pageMod = require('sdk/page-mod');
var panels = require('sdk/panel');
var simpleStorage = require('sdk/simple-storage')
var notifications = require('sdk/notifications');
var tabs = require("sdk/tabs");

var selectors = []; 
var matchers = [];
var annotatorIsOn = false;
var scriptRules = data.load('scripts/default-matcher.json');

if (!simpleStorage.storage.annotations)
  simpleStorage.storage.annotations = [];

function updateMatchers() {
  matchers.forEach(function (matcher) {
    var arr = new Array();
    var stringifiedJSON = JSON.stringify(simpleStorage.storage.annotations);
    arr[0] = stringifiedJSON;
    arr[1] = scriptRules;
    matcher.postMessage(arr);
  });
}

function handleNewAnnotation(anchor) {
  var newAnnotation = new Annotation(anchor);  
  simpleStorage.storage.annotations.push(newAnnotation);
  updateMatchers();
}

function Annotation(anchor) {
  this.url = anchor[0];
  this.payload = anchor[1];
}

function activateSelectors() {

    var arr = new Array();
    arr[0] = annotatorIsOn;
    arr[1] = scriptRules;
    
  selectors.forEach(
    function (selector) {
      selector.postMessage(arr);
  });
}
 
function toggleActivation() {
  annotatorIsOn = !annotatorIsOn;
  activateSelectors();
  return annotatorIsOn;
}

 
function detachWorker(worker, workerArray) {
  var index = workerArray.indexOf(worker);
  if(index != -1) {
    workerArray.splice(index, 1);
  }
}

function showProps(obj, objName) {
  var result = "";
  for (var i in obj) {
    if (obj.hasOwnProperty(i)) {
        result += objName + "." + i + " = " + obj[i] + "\n";
    }
  }
  return result;
}
 
function Putter(){
   var sendUpdate; 
}

Putter.prototype.putAnnotations = function(){
  simpleStorage.storage.annotations.forEach(
    function(storedAnnotation) {
      sendUpdate = Request({
      url: 'http://localhost:8080/anchors',
      content: {"url":storedAnnotation.url, "payload":storedAnnotation.payload},
      onComplete: function(response) {
        console.log('url:' + storedAnnotation.url + ' payload: ' + storedAnnotation.payload)
        console.log('Server response:' + response.text + ', Code: ' + response.status + ', Text: ' + response.statusText);
      }
    });
    console.log('request: '+ showProps(sendUpdate.content), "request");
    sendUpdate.put();
  });
}

exports.main = function() {
    
   var getUpdates = Request({
      url: 'http://localhost:8080/anchors.json',
      onComplete: function(response) {
        for(var i = 0; i<response.json.length; i++){
            var r = response.json[i];
            handleNewAnnotation(new Array(r.url, r.payload));
        };
      }
    });
    getUpdates.get();

    var widget = widgets.Widget({
        id: 'toggle-switch',
        label: 'Social Weaver',
        contentURL: data.url('widget/grey-socialweaver-logo.png'),
        contentScriptWhen: 'ready',
        contentScriptFile: data.url('widget/widget.js')
    });
    
    widget.port.on('left-click', function() {
        if(toggleActivation()){
            widget.contentURL = data.url('widget/black-socialweaver-logo.png')
        }else{
            widget.contentURL = data.url('widget/grey-socialweaver-logo.png');
            tabs.activeTab.reload();
        }
    });
    var putter = new Putter();
    widget.port.on('right-click', function() {
      annotationList.show();
      putter.putAnnotations();
    });
  
 
  
    var selector = pageMod.PageMod({
      include: ['*'],
      contentScriptWhen: 'ready',
      contentScriptFile: [data.url('jquery.js'),
                          data.url('domtree.js'),
                          data.url('selector.js')],
      onAttach: function(worker) {
        worker.postMessage(annotatorIsOn);
        selectors.push(worker);
        worker.port.on('show', function(data) {
          annotationEditor.annotationAnchor = data;
          annotationEditor.show();
        });
        worker.on('detach', function () {
          detachWorker(this, selectors);
        });
      }
    });
    
    var annotationEditor = panels.Panel({
      width: 200,
      height: 300,
      contentURL: data.url('editor/annotation-editor.html'),
      contentScriptFile: data.url('editor/annotation-editor.js'),
      onMessage: function(annotationText) {
        if (annotationText) {
            handleNewAnnotation(this.annotationAnchor);
        }
        notifications.notify({
            title: 'Success!',
            text: 'Annotation has been successfully persisted!'
        });
        annotationEditor.hide();
      },
      onShow: function() {
        this.postMessage('focus');
      }
    });
    
    var annotationList = panels.Panel({
      width: 420,
      height: 600,
      contentURL: data.url('list/annotation-list.html'),
      contentScriptFile: [data.url('jquery.js'),
                          data.url('list/annotation-list.js')],
      contentScriptWhen: 'ready',
      onShow: function() {
        this.postMessage(simpleStorage.storage.annotations);
      },
      onMessage: function(message) {
        require('sdk/tabs').open(message);
      }
    });
     simpleStorage.on("OverQuota", function () {
      notifications.notify({
        title: 'Storage space exceeded',
        text: 'Removing recent annotations'});
      while (simpleStorage.quotaUsage > 1)
        simpleStorage.storage.annotations.pop();
    });
    
    var matcher = pageMod.PageMod({
      include: ['*'],
      contentScriptWhen: 'ready',
      contentScriptFile: [data.url('jquery.js'),
                          data.url('domtree.js'),
                          data.url('matcher.js')],
      onAttach: function(worker) {
        if(simpleStorage.storage.annotations) {
          worker.postMessage(simpleStorage.storage.annotations);
        }
        worker.port.on('show', function(data) {
          annotation.content = data;
          annotation.show();
        });
        worker.port.on('hide', function() {
          annotation.content = null;
          annotation.hide();
        });
        worker.on('detach', function () {
          detachWorker(this, matchers);
        });
        matchers.push(worker);
        updateMatchers();
      }
    });
    
    var annotation = panels.Panel({
      width: 200,
      height: 180,
      contentURL: data.url('annotation/annotation.html'),
      contentScriptFile: [data.url('jquery.js'),
                          data.url('annotation/annotation.js')],
      onShow: function() {
        this.postMessage(this.content);
      }
    });
}

