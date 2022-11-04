package miniproject.board.service;

import miniproject.board.domain.Member;
import miniproject.board.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 데이터를 저장하고 변경할 때는 서비스 쪽에 @Transcational이 있어야한다.
// JPA를 통한 모든 데이터 변경은 @Transactional 안에서 실행해야 한다.
@Service
@Transactional
public class MemberService {

    // Memory가 아닌 다른 방법(DB연결)으로 구현체를 수정하면 MemberService를 수정하지 않고도
    // 데이터 저장소를 바꿀 수 있게 하기 위해 다형성을 활용하기 위해 인터페이스를 사용
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 가입
    public Long memberJoin(Member member) {
        return repository.save(member).getId();
    }

    // 전체 조회
    public List<Member> findAllMembers() {
        return repository.findAll();
    }

    // 한명 조회
    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }
}
