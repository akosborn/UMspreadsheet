package com.umspreadsheet.v1.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Set findById(Long id)
    {
        return setRepository.findOne(id);
    }

    public void delete(Long id)
    {
        setRepository.delete(id);
    }
}
