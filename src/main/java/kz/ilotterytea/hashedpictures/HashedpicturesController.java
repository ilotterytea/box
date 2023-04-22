package kz.ilotterytea.hashedpictures;

import io.micronaut.http.annotation.*;

@Controller("/hashedpictures")
public class HashedpicturesController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}