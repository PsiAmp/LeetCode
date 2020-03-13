package problems;

import java.util.HashSet;

public class UniqueEmailAddresses_929 {

    public int numUniqueEmails(String[] emails) {
        HashSet<String> emailSet = new HashSet<>();

        for (int i = 0; i < emails.length; i++) {
            int indexOfAt = emails[i].indexOf("@");
            String prefix = emails[i].substring(0, indexOfAt);

            int indexOfPlus = prefix.indexOf("+");
            if (indexOfPlus != -1)
                prefix = prefix.substring(0, indexOfPlus);

            prefix = prefix.replace(".", "");

            String email = prefix + emails[i].substring(indexOfAt, emails[i].length());
            emailSet.add(email);
        }

        return emailSet.size();
    }

    String ee[]= {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};

    public static void main(String[] args) {
        UniqueEmailAddresses_929 uniqueEmailAddresses_929 = new UniqueEmailAddresses_929();
        System.out.println(uniqueEmailAddresses_929.numUniqueEmails(uniqueEmailAddresses_929.ee));
    }

}
