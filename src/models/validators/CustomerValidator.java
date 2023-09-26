package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Customer;
import utils.DBUtil;

public class CustomerValidator {
    public static List<String> validate(Customer c, Boolean idDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String member_id_error = validateId(c.getMember_id(), idDuplicateCheckFlag);
        if(!member_id_error.equals("")) {
            errors.add(member_id_error);
        }

        String name_error = validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(c.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // 会員ID
    private static String validateId(String member_id, Boolean idDuplicateCheckFlag) {
        // 必須入力チェック
        if(member_id == null || member_id.equals("")) {
            return "会員IDを入力してください。";
        }

        // すでに登録されている社員番号との重複チェック
        if(idDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long customer_count = (long)em.createNamedQuery("checkRegisteredMemberId", Long.class).setParameter("member_id", member_id).getSingleResult();
            em.close();
            if(customer_count > 0) {
                return "入力された会員IDの情報はすでに存在しています。";
            }
        }

        return "";
    }

    // 社員名の必須入力チェック
    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}