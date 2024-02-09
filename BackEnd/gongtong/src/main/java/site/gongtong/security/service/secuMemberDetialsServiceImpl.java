//package site.gongtong.security.service;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import site.gongtong.security.secumodel.secuMember;
//import site.gongtong.security.secumodel.secuMemberDetails;
//import site.gongtong.security.repository.secuMemberDetialsRepository;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class secuMemberDetialsServiceImpl implements secuMemberDetialsService {
//    private final secuMemberDetialsRepository secuMemberDetialsRepo;
//
//    @Override
//    public secuMemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        secuMember member = secuMemberDetialsRepo.findById(username)
//                .orElseThrow(() -> {
//                    return new UsernameNotFoundException("Cannot find the input Member");
//                });
//        return new secuMemberDetails(member);
//    }
//}
