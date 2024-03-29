package microservice.springboot.hazelcast.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("hazelcast1")
@CacheConfig(cacheNames = "my-map")
@Slf4j
public class HazelcastController {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public HazelcastController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @CacheEvict(allEntries = true)
    public void clearCache(){}

    @PostMapping("/write-data")
    public String writeDataToHazelcast(@RequestParam String key, @RequestParam String value) {
        IMap<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        hazelcastMap.put(key, value);
        return "Data is stored";
    }

    @GetMapping("/read-data")
    @Cacheable(condition = "#key.equals('hamed')")
    public String readDataFromHazelcast(@RequestParam String key) {
        log.info("################################ Read data from hazelcast map for " + key);
        IMap<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastMap.get(key);
    }

    @GetMapping("/read-all-data")
    public Map<String, String> readAllDataFromHazelcast() {
        return hazelcastInstance.getMap("my-map");
    }

}
