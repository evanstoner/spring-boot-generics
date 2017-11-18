# spring-boot-generics

_Generic controllers and services for Spring Boot applications._

Boilerplate controllers, services, and entity for getting a Spring Boot application
off the ground. Override and customize when you're ready.

## Usage

### Entity

`net.evanstoner.spring.entity`

- `BaseEntity` - Basic entity with a sequenced `Long` ID and a sensible `toString()`.

```java
import com.fasterxml.jackson.annotation.JsonRootName;
import net.evanstoner.spring.entity.BaseEntity;
import javax.persistence.Entity;

@Entity
@JsonRootName("color")
public class Color extends BaseEntity {

    private int red;
    private int green;
    private int blue;

    // <getters and setters>
}

```

### Repository

There's no repository assistance provided by this library. Using the `CrudRepository`
built into the Spring framework is sufficient.

```java
import org.springframework.data.repository.CrudRepository;

public interface ColorRepository extends CrudRepository<Color, Long> {

    // The CrudRepository supports a number of by-ID methods, but let's add one
    // matching or domain.
    Color findByRedAndGreenAndBlue(int red, int green, int blue);

}
```

### Services

`net.evanstoner.spring.service`

- `GenericReadOnlyService` interface
- `GenericReadOnlyServiceImpl`
- `GenericReadWriteService` interface
- `GenericReadWriteServiceImpl`

```java
import net.evanstoner.spring.service.GenericReadWriteService;
import org.springframework.stereotype.Service;

@Service
public interface ColorService extends GenericReadWriteService<Color> {

    public Color findByRGB();

    public Color findByHex();

}
```

```java
import net.evanstoner.spring.service.GenericReadWriteServiceImpl;

public class ColorServiceImpl extends GenericReadWriteServiceImpl<Color, ColorRepository> implements ColorService {

    @Override
    public Color findByRGB(int red, int green, int blue) {
        return this.repository.findByRedAndGreenAndBlue(red, green, blue);
    }

    @Override
    public Color findByHex(String hex) {
        String validHexRegex = "[0-9a-fA-F]{6}";
        if (!hex.matches(validHexRegex)) {
            throw new IllegalArgumentException("hex must match " + validHexRegex);
        }
        int red = Integer.parseInt(hex.substring(0, 2), 16);
        int green = Integer.parseInt(hex.substring(2, 4), 16);
        int blue = Integer.parseInt(hex.substring(4, 6), 16);
        return this.repository.findByRedAndGreenAndBlue(red, green, blue);
    }
}
```

### Controllers

`net.evanstoner.spring.controller`

- `GenericBaseController`
- `GenericReadOnlyController`
- `GenericReadWriteController`

```java
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
```

You now have an API and persistence layer supporting:

- `POST   /colors`
- `GET    /colors`
- `GET    /colors/{id}`
- `GET    /colors?hex=fae0b9`
- `DELETE /colors/{id}`
