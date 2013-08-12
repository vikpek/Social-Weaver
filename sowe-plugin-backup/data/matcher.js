var scriptRules = null;

self.on('message', function onMessage(arr) {

    annotations = JSON.parse(arr[0]);
    scriptRules = arr[1];
    
    annotations.forEach(
    function(annotation) {
        if(annotation.url == document.location.toString()) {
            createAnchor(annotation);
        }
    });
    
    $('.annotated').css('border', 'solid 2px green');
    //$('.annotated').css('background', 'solid 1px green');
    
    $('.annotated').bind('mouseenter', function(event) {
        self.port.emit('show', $(this).attr('annotation'));
        event.stopPropagation();
        event.preventDefault();
    });
    
    $('.annotated').bind('mouseleave', function() {
        self.port.emit('hide');
    });
});
 
function createAnchor(annotation) {
    var payload = annotation.payload;
    var arr = JSON.parse(payload);
    
    var matched = new Boolean();
    matched = false;
    
    
    var rules = null;
    if(typeof(scriptRules) == 'undefined'){
        console.error('Undefinded script rules');    
    }else{
        var rArr = JSON.parse(scriptRules);
        rules = rArr.rules;
    }
    
    // TODO triple-for - ugly ........... 
    var matchedElement = null;
    $("*").each(
        function(indx, curEl){
            matchedElement = $(this);
            var noRuleFailure = new Boolean(true);  
            
            var curCmdEval = null;
            var payloadEval = null;
            arr.forEach(
                function(el){
                    for (var akey in el) {
                        if (el.hasOwnProperty(akey)) {
                                rules.forEach(
                                    function(rule){
                                        for (var rkey in rule) {
                                            if (rule.hasOwnProperty(rkey) && noRuleFailure) {
                                                if(rkey === akey){
                                                    curCmdEval = eval(rule[rkey]);
                                                    payloadEval = el[akey];
                                                    if(curCmdEval != payloadEval || !curCmdEval || !payloadEval){
                                                        noRuleFailure = false;
                                                        return false;
                                                    }
                                                }
                                            }
                                        }
                                });
                        }
                    }
            });

            if(noRuleFailure){
                console.log('Found match');
                matchedElement.addClass('annotated');
                return true;
            }
    });
}