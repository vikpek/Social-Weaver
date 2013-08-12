var matchedElement = null;
var originalBgColor = null;
var active = false;
var scriptRules = null;
 
function resetMatchedElement() {
  if (matchedElement) {
    $(matchedElement).css('background-color', originalBgColor);
    $(matchedElement).unbind('click.annotator');
  }
}
 
self.on('message', function onMessage(arr) {
      
  active = arr[0];
  scriptRules = arr[1];
  
  if (!active) {
    resetMatchedElement();
  }else{
        var counter = 0;
        $(":visible").each(
        function(indx, curEl){
            if($(this).text()){
                $(this).css('border','0.5px dotted lightgreen');
            } 
            counter++;
        });
    //console.log('Matchable elements: ' + counter);

  }
});

$(":visible").filter(function(index) {
  if($(this).text()){
      return true;
  }else{
      return false;
  }
}).mouseenter(function() {
        
      if (!active || $(this).hasClass('annotated')) {
        return;
      }
      resetMatchedElement();
      matchedElement = $(this);
      //console.log(matchedElement);
      originalBgColor = $(matchedElement).css('background-color');
  
    var url = document.location.toString();
    
    // generate dom path
    var rightArrowParents = [];
    $(this).parents().not('html').each(function() {
        var entry = this.tagName.toLowerCase();
        if (this.className) {
            entry += "." + this.className.replace(/ /g, '.');
        }
        rightArrowParents.push(entry);
    });
    rightArrowParents.reverse();
    
    var domPath = rightArrowParents.join(";");
    // dom path end
    
    
    // generate unique dom path
    var uniqueDomPath = "";
    $(document).children().each(function(){
        //todo enter recursive function here to reach all kids...
    });
    
    console.log('uniquedomPath: ' + uniqueDomPath);
    // unique dom path end

    matchedElement.css('background-color', 'green');

    $(matchedElement).bind('click.annotator', function(event) {
        var arr = {
            payload: []
        };
        
        if(typeof(scriptRules) == 'undefined'){
            console.error('Undefinded script rules');    
        }else{
            var rArr = JSON.parse(scriptRules);
        }
          
        rArr.rules.forEach(
        function(el){
            for (var key in el) {
                if (el.hasOwnProperty(key)) {
                    var o = {};
                    if(key==='dom_path'){
                      o['dom_path'] = domPath;
                    }else{
                        o[key] = eval(el[key]);
                        console.log('Key: ' + el[key] + ' eval: ' + eval(el[key]));
                        
                        if(typeof(eval(el[key])) == 'undefined'){
                            console.error('Error in script while evaluating: ' + el[key]);
                        }
                        
                        if(o.length === 0){
                            o[key] = el[key];
                        }
                    }
                    arr.payload.push(o);
                }
            }
    });
    
    event.stopPropagation();
    event.preventDefault();    
        self.port.emit('show',
          [
            url,
            JSON.stringify(arr.payload)
          ]
    );
    });
});

$('*').mouseout(function() {
  resetMatchedElement();
});