package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;
    //다른 인스턴스가 계속 생기면 다른 static 변수 선언 안된거 같은 경우는 다른 db가 생기게 된다.
    // 그래서 Test랑 같이 memberRepository를 공유하려면, 아래와 같이 하면된다.

    //직접 MemberRepository 를 생성하지 않고 밖에서 넣어줌 = >이러면 repository를 두개 안 만들어도 됨.
    //이런 구조를 Dependency Injection = DI라고 함.

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public long join(Member member){
        //같은 이름이 있는 중복회원은 x일 때,

            validateDuplicateMember(member);//중복 회원 검증.
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }


    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
