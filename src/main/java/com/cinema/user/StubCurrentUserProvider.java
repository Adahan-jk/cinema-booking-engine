package com.cinema.user;

import org.springframework.stereotype.Component;

@Component
public class StubCurrentUserProvider implements CurrentUserProvider {
    @Override
    public Long currentUserId() {
        return 1L;
    }
}
