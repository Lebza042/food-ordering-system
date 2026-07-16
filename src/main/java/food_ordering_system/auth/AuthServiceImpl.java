package food_ordering_system.auth;

import food_ordering_system.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private static final String DEFAULT_ROLE = "CUSTOMER";
    private static final String GENERIC_LOGIN_ERROR = "Invalid email or password";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AuthDto.RegisterResponse register(AuthDto.RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        Role customerRole = roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException(
                        "Required role '" + DEFAULT_ROLE + "' is missing - check RoleSeeder ran on startup"));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .active(true)
                .roles(List.of(customerRole))
                .build();

        userRepository.save(user);

        return AuthDto.RegisterResponse.builder()
                .message("Registration successful. You can now log in.")
                .build();
    }

    @Override
    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        // Same generic error whether the email doesn't exist OR the password
        // is wrong - never reveal which one was incorrect to an attacker.
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(GENERIC_LOGIN_ERROR));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException(GENERIC_LOGIN_ERROR);
        }

        if (!user.isActive()) {
            throw new BadRequestException("Your account is inactive. Please contact support.");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        return AuthDto.LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .name(user.getName())
                .roles(roleNames)
                .build();
    }
}