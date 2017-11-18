package net.evanstoner.spring.test;

import net.evanstoner.spring.controller.GenericReadWriteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/colors")
public class ColorController extends GenericReadWriteController<Color, ColorService> {

    @RequestMapping(method = RequestMethod.GET, params = "hex")
    public Map getByHex(@RequestParam("hex") String hex) {
        return wrapOne(this.service.findByHex(hex));
    }

}
