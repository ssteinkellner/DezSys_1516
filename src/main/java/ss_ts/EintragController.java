package ss_ts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EintragController {

    @RequestMapping("/eintrag")
    public Eintrag get(@RequestParam(value="titel",defaultValue="") String titel) {
        return Eintrag.load(titel);
    }
}
