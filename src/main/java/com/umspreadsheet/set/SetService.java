package com.umspreadsheet.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetService
{
    private SetRepository setRepository;

    @Autowired
    public SetService(SetRepository setRepository)
    {
        this.setRepository = setRepository;
    }

    public Set save(Set set)
    {
        return setRepository.save(set);
    }

    public Optional<Set> findById(Long id)
    {
        return setRepository.findById(id);
    }

    public void delete(Long id)
    {
        setRepository.deleteById(id);
    }
}
