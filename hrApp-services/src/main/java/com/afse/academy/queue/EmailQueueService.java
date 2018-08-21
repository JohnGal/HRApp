package com.afse.academy.queue;

import com.afse.academy.persistence.entities.Email;

public interface EmailQueueService {

    void send(Email email);
}
