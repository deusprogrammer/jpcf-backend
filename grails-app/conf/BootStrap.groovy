import com.jpcf.backend.domain.User

class BootStrap {

    def init = { servletContext ->
        //Admin User
        def user = User.findByUsername("admin")
        if (!user) {
            user = new User(username: "admin", password: "password")
            user.save(flush: true)
            println "*USER: " + user
        }
    }
    def destroy = {
    }
}
