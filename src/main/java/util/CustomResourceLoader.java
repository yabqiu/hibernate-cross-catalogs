package util;

import org.springframework.core.io.DefaultResourceLoader;

public class CustomResourceLoader extends DefaultResourceLoader {

    public CustomResourceLoader() {
        super(new SegregatedClassLoader());
    }

}
