package sp.learn.spring.lab.randomclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "fegin")
@EnableFeignClients
public class FeginController {

    @Autowired
    RandomServer randomServer;

    @GetMapping(path = "/")
    public String get() {
        return randomServer.get();
    }
}

@FeignClient(value = "randomserver")
interface RandomServer {
    @GetMapping(path = "/random/")
    String get();
}