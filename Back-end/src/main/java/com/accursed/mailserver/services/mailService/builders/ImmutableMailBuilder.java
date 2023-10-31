package com.accursed.mailserver.services.mailService.builders;

import com.accursed.mailserver.models.ImmutableMail;

public class ImmutableMailBuilder extends MailBuilder{
    private static ImmutableMailBuilder instance;

    private ImmutableMailBuilder(){}

    public static synchronized ImmutableMailBuilder getInstance(){
        if(instance == null)
            instance = new ImmutableMailBuilder();
        return instance;
    }

    @Override
    public void reset() {
        instance = new ImmutableMailBuilder();
    }

    @Override
    public ImmutableMail getResult() {
        return new ImmutableMail(mailFrom, mailTo, subject, content, date,
                state, isStarred, priority, attachments);
    }
}
