package com.decoded.messaging.repositories;

import java.util.List;

import com.decoded.messaging.data.entities.Message;
import com.decoded.messaging.data.entities.MessageStatus;
import com.decoded.messaging.data.entities.MessageType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Message> S save(S entity);

    @Override
    @RestResource(exported = false)
    void delete(Message message);

    @RestResource(path = "filter/status", description = @Description(value = "Find By Status"))
    Page<Message> findByStatus(MessageStatus status, Pageable pageable);

    @RestResource(path = "eligible/to/send", description = @Description(value = ""))
    Page<Message> findByStatusAndRetriesLessThan(MessageStatus status, Integer retries, Pageable pageable);

    @RestResource(path = "filter/type/status", description = @Description(value = ""))
    Page<Message> findByTypeAndStatus(MessageType type, MessageStatus status, Pageable pageable);

}