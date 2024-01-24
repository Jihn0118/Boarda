package site.gongtong.moim.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.repository.MoimRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoimServiceImpl implements MoimService {
    private final MoimRepository moimRepository;

    @Override
    public List<Moim> getMoimList(String location) {
        return moimRepository.getMoimsByLocation(location);
    }

    @Override
    public List<Moim> getDeadlineList() {
        return moimRepository.getMoimsDeadLine();
    }
}
