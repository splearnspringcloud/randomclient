package sp.learn.spring.lab.randomclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "basic")
public class BasicController {
    private static final Logger log = LoggerFactory.getLogger(BasicController.class.getName());

    @Autowired
    DiscoveryClient client;

    @GetMapping(path = "/")
    public String get() {
        List<ServiceInstance> serviceInstanceList = client.getInstances("randomserver");
        if (serviceInstanceList == null || serviceInstanceList.size() == 0) {
            return "Random server not found";
        } else {

            serviceInstanceList.forEach(i -> {
                log.info(i.getHost());
            });

            String host = serviceInstanceList.get(0).getHost();
            return new RestTemplate().getForObject(String.format("http://%s/random/", host), String.class);
        }
    }
}
