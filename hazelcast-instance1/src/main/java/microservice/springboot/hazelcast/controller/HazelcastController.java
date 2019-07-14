package microservice.springboot.hazelcast.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("hazelcast1")
public class HazelcastController {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public HazelcastController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping("/write-data")
    public String writeDataToHazelcast(@RequestParam String key, @RequestParam String value) {
        IMap<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        hazelcastMap.put(key, value);
        return "Data is stored";
    }

    @GetMapping("/read-data")
    public String readDataFromHazelcast(@RequestParam String key) {
        IMap<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastMap.get(key);
    }

    @GetMapping("/read-all-data")
    public Map<String, String> readAllDataFromHazelcast() {
        return hazelcastInstance.getMap("my-map");
    }

}
