package com.chatbot.deltour.repository;

import com.chatbot.deltour.model.member.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Member findByEmail(String email);

}
