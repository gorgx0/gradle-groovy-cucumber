import org.slf4j.LoggerFactory
import ratpack.form.Form
import ratpack.http.Status

import java.nio.file.Paths

import static ratpack.groovy.Groovy.ratpack

final String EATER_ID_COOKIE = "EATER-ID-COOKIE"

final LOGGER = LoggerFactory.getLogger("ratpack")

ratpack {


    serverConfig {
        c -> c.baseDir(Paths.get('/home/gorg/tmp/ratpack04/app01/src/ratpack'))
    }


    handlers {
        files {
            dir("static")
        }

        all{ ctx ->
            LOGGER.debug("URI: {}", ctx.request.uri)
            def eaterCookie = ctx.request.cookies.find {it.name().equals(EATER_ID_COOKIE)}
            if (request.path != 'registration' && eaterCookie == null){
                redirect(307, '/registration')
            }else {
                ctx.next()
            }
        }

        path("registration") {
            byMethod {
                get {
                    render("Registration")
                }
                post {
                    parse(Form).then { f->
                        if (f.userName) {
                            response.cookie(EATER_ID_COOKIE,"registered_user")
                            render("Registered")
                        }else {
                            response.status(Status.BAD_REQUEST)
                            render("No userName given")
                        }
                    }
                }
            }
        }
    }
}
