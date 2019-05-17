import java.nio.file.Paths

import static ratpack.groovy.Groovy.ratpack

final String EATER_ID_COOKIE = "EATER-ID-COOKIE"


ratpack {


    serverConfig {
        c -> c.baseDir(Paths.get('/home/gorg/tmp/ratpack04/app01/src/ratpack'))
    }


    handlers {
        files {
            dir("static")
        }

        all{ ctx ->
            println "******************************"
            println ctx.request.uri
            println "******************************"
            def eaterCookie = ctx.request.cookies.find {it.name().equals(EATER_ID_COOKIE)}
            if (request.path != 'registration' && eaterCookie == null){
                redirect(307, '/registration')
            }else {
                ctx.next()
            }
        }

        get("registration") {
            render("Registration")
        }

        post("registration") {
            response.cookie(EATER_ID_COOKIE,"registered_user")
        }

    }
}
