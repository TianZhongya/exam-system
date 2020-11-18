package com.example.tzy.demo.biz.common;

import javax.validation.groups.Default;

/**
 * @author: Tianzy
 * @create: 2020-11-17 16:55
 **/
public class Validations {
    public Validations() {
    }

    public interface ValidateChoice extends Default{
    }

    public interface ValidateTF extends Default {
    }

    public interface ValidateMultiAnswerChoice extends Default {
    }

    public interface ValidateShortAnswer extends Default {
    }
}
