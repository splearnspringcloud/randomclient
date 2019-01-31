package sp.learn.spring.lab.randomclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "fegin")
@EnableFeignClients
public class FeginController {

    @Autowired
    RandomServer randomServer;

    @GetMapping(path = "/")
    public String get() {
        return String.format("Random Client Fegin - %s", randomServer.get());
    }

    @GetMapping(path = "/hystrix/")
    @HystrixCommand(fallbackMethod = "getFallback")
    public String getHys() {
        return String.format("Random Client Fegin Hystrix - %s", randomServer.get());
    }

    private String getFallback() {
        return "Random Server Not Available!";
    }
}

@FeignClient(value = "randomserver")
interface RandomServer {
    @GetMapping(path = "/random/")
    String get();
}