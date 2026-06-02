package result.factory;

import enums.ResultOrderType;
import result.strategy.AuthorNameOrder;
import result.strategy.RecentlyPublishedOrder;
import result.strategy.RecentlySearchedOrder;
import result.strategy.RecentlyUpdatedOrder;
import result.strategy.ResultOrderStrategy;

public class ResultOrderFactory {
    public static ResultOrderStrategy createResultOrderStrategy(ResultOrderType resultOrderType) {
        return switch (resultOrderType) {
            case RECENTLY_PUBLISHED -> new RecentlyPublishedOrder();
            case RECENTLY_UPDATED -> new RecentlyUpdatedOrder();
            case RECENTLY_SEARCHED -> new RecentlySearchedOrder();
            case AUTHOR_NAME -> new AuthorNameOrder();
            default -> null;
        };
    }
}