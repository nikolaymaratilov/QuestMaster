package app.utils;

import app.subscription.model.SubscriptionType;
import app.user.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WalletUtilis {
    public static boolean isEligibleToUnlockNewWallet(User user) {

        SubscriptionType subscriptionType = user.getSubscriptions().get(0).getType();
        int walletsSize = user.getWallets().size();

        return (subscriptionType == SubscriptionType.PREMIUM && walletsSize < 2) || (subscriptionType == SubscriptionType.ULTIMATE && walletsSize < 3);
    }
}
