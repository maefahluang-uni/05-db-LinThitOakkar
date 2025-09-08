package th.mfu.boot;
import java.util.Optional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    //TODO: add userrepository as `public` with @Autowired
    @Autowired
    public UserRepository repo;
   
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
         User existingUser = repo.findByUsername(user.getUsername());
        if (existingUser != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }

        // Save new user
        repo.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
          List<User> users = repo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
       
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> userOpt = repo.findById(id);
        if (!userOpt.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        repo.deleteById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }


}
