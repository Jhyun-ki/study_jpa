package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//field injection  : access 할 수 있는 방법이 없기 때문에 바꿀수가없다. (테스트 시에 바꿔야 할수도 있음(가짜리파지토리를 주입이 불가))
//setter injection : 애플리케이션 러닝타임에 누가 해당 빈을 바꿀수도있는 단점이 있다... 그래서는 안되는데(가짜 리파지토리 주입이 가능)


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //실무에서는 was가 여러개 떠있기 때문에(멀티 쓰레드 상황) 동시에 같은 이름으로 접근할수가있다.
    //그렇기 때문에 실무에서는 member.name에 유니크 제약조건을 걸어두는편이 좋다.
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
