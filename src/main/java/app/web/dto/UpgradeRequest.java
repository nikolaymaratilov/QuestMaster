package app.web.dto;

import app.subscription.model.SubscriptionPeriod;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpgradeRequest {

    private SubscriptionPeriod period;

    private UUID walletId;
}
