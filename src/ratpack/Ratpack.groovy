import java.nio.file.Paths

import static ratpack.groovy.Groovy.ratpack


ratpack {


    serverConfig {
        c -> c.baseDir(Paths.get('/home/gorg/tmp/ratpack04/app01/src/ratpack'))
    }


    handlers {
        files {
            dir("static")
        }
    }
}
