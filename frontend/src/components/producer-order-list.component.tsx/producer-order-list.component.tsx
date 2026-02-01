import type { ProducerOrder } from "../../models/producer-order.model";
import { ProducerOrderCard } from "../cards/producer-order-card/producer-order.card";

interface IProps {
  producerOrderList: ProducerOrder[];
}

export const ProducerOrderList = ({ producerOrderList }: IProps) => {
  return (
      <div className="mx-auto w-full max-w-4xl space-y-6 px-4 py-8">
        {producerOrderList.map((producerOrder) => (
            <ProducerOrderCard
                key={producerOrder.producerOrderId}
                producerOrder={producerOrder}
            />
        ))}
      </div>
  );
};
