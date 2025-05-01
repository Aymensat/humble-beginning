package com.aymen_yassine.first_site;

import com.aymen_yassine.first_site.entity.Teacher;
import com.aymen_yassine.first_site.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    // No AdminRepository needed!

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if it's the hardcoded admin
        if ("AymenSama".equalsIgnoreCase(username)) {
            return User.builder()
                    .username("AymenSama")
                    .password("$2a$10$Tg1rmsIOcV4Do1miDgU4HOPM3/ooFRfiXpcgSC4zTpscphzQXQYnW") // hashed "AymenSama"
                    .roles("ADMIN")
                    .build();
        }

        // Otherwise look for a teacher
        Teacher teacher = (Teacher) teacherRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher not found"));

        return User.builder()
                .username(teacher.getEmail())
                .password(teacher.getPassword())
                .roles("TEACHER")
                .build();
    }
}