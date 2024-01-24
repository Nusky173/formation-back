package group.fortil.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goodbye")
public class HelloControler {
    @GetMapping()
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Goodbye test %s!", name);
    }
}