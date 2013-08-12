function getDomPath(){
    var rightArrowParents = [];
    $(this).parents().not('html').each(function() {
        var entry = this.tagName.toLowerCase();
        if (this.className) {
            entry += "." + this.className.replace(/ /g, '.');
        }
        rightArrowParents.push(entry);
    });
    rightArrowParents.reverse();
    console.log("Right parents: " + rightArrowParents.join(" "));
}