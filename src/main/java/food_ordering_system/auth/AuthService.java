package food_ordering_system.auth;

public interface AuthService {
    AuthDto.RegisterResponse register(AuthDto.RegisterRequest request);
}