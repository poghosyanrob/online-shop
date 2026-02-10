package am.example.onlineshop.service.impl;

import am.example.onlineshop.model.User;
import am.example.onlineshop.repository.MembersRepository;
import am.example.onlineshop.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    private final MembersRepository membersRepository;

    @Override
    public List<User> findAll() {
        return membersRepository.findAll();
    }

    @Override
    public void save(User member) {
        membersRepository.save(member);
    }

    @Override
    public void deleteById(Integer id) {
            membersRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return membersRepository.findById(id);
    }
}
