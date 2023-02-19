package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    //스프링 데이터 JPA : 인터페이스 이름 만으로도 개발이 완료!! 되게편함.
    //JPQL select m from Member m where m.name =?
    @Override
    Optional<Member> findByName(String name);
}
