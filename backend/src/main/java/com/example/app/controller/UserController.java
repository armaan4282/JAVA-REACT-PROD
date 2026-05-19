@PostMapping("/login")
public String login(@RequestBody User user) {

    return repository.findByEmail(user.getEmail())
            .map(dbUser -> {
                if (dbUser.getPassword() != null &&
                    dbUser.getPassword().equals(user.getPassword())) {
                    return "Login Success";
                }
                return "Invalid Credentials";
            })
            .orElse("Invalid Credentials");
}
