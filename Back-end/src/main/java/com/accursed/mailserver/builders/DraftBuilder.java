package com.accursed.mailserver.builders;

import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;

public class DraftBuilder extends MailBuilder{
    private static DraftBuilder instance;

    private DraftBuilder(){}

    public static synchronized DraftBuilder getInstance(){
        if(instance == null)
            instance = new DraftBuilder();
        return instance;
    }

    @Override
    public void reset() {
        instance = new DraftBuilder();
    }

    @Override
    public DraftMail getResult() {
        return new DraftMail(mailFrom, mailTo, subject, content, date,
                state, isStarred, priority, senderID, receiverID);
    }
}
