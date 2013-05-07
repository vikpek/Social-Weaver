package at.ac.uibk.qe.sowe.controller;

import at.ac.uibk.qe.sowe.Anchor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/anchors")
@Controller
@RooWebScaffold(path = "anchors", formBackingObject = Anchor.class)
@RooWebJson(jsonObject = Anchor.class)
public class AnchorController {

    @RequestMapping(value="/updates/{lastModifiedTimestamp}")
    public ResponseEntity<String> listNewAnchorsJson(@PathVariable Long lastModifiedTimestamp) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Anchor> result = Anchor.findAllAnchorsWithNewerTimestamp(lastModifiedTimestamp);
        return new ResponseEntity<String>(Anchor.toJsonArray(result), headers, HttpStatus.OK);
    }

//    @RequestMapping(value="/updates/{lastModifiedTimestamp}", method = RequestMethod.GET)
//    public String showNewAnchors(@PathVariable Long lastModifiedTimestamp, Model uiModel) {
//        uiModel.addAttribute("anchor", Anchor.findAllAnchorsWithNewerTimestamp(lastModifiedTimestamp));
//        return "anchors/show";
//    }
}
