package um.edu.bagues.BaguesApp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BaguesAppController {

    @GetMapping("/index")
    public String index() {
        return "Welcome to BaguesApp!";
    }
}
