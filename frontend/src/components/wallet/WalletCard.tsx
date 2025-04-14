
import React from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { PlusCircle, CreditCard, Clock } from "lucide-react";

interface TransactionProps {
  id: string;
  amount: number;
  type: 'deposit' | 'withdrawal' | 'payment';
  description: string;
  date: string;
}

interface WalletCardProps {
  balance: number;
  transactions: TransactionProps[];
  onAddFunds?: () => void;
}

const WalletCard: React.FC<WalletCardProps> = ({
  balance,
  transactions,
  onAddFunds
}) => {
  return (
    <Card>
      <CardHeader className="flex flex-row items-center justify-between pb-2 space-y-0">
        <CardTitle className="text-2xl">Your Wallet</CardTitle>
        <Button onClick={onAddFunds} className="bg-airline-blue hover:bg-airline-navy">
          <PlusCircle className="mr-2 h-4 w-4" />
          Add Funds
        </Button>
      </CardHeader>
      <CardContent>
        <div className="flex items-center justify-between p-4 bg-airline-blue/10 rounded-lg mb-6">
          <div>
            <p className="text-sm text-gray-500">Current Balance</p>
            <h3 className="text-3xl font-bold">₹{(balance * 83).toFixed(2)}</h3>
          </div>
          <CreditCard className="h-12 w-12 text-airline-blue opacity-70" />
        </div>

        <h4 className="font-medium text-lg mb-4">Recent Transactions</h4>
        
        <div className="space-y-4">
          {transactions.length > 0 ? transactions.map(transaction => (
            <div key={transaction.id} className="flex justify-between items-center p-3 border rounded-lg">
              <div className="flex items-center space-x-4">
                <div className={`p-2 rounded-full ${
                  transaction.type === 'deposit' ? 'bg-green-100 text-green-600' : 
                  transaction.type === 'withdrawal' ? 'bg-red-100 text-red-600' : 
                  'bg-blue-100 text-blue-600'
                }`}>
                  {transaction.type === 'deposit' ? <PlusCircle className="h-4 w-4" /> : 
                   transaction.type === 'withdrawal' ? <CreditCard className="h-4 w-4" /> :
                   <Clock className="h-4 w-4" />}
                </div>
                <div>
                  <p className="font-medium">{transaction.description}</p>
                  <p className="text-sm text-gray-500">{transaction.date}</p>
                </div>
              </div>
              <div className={`font-semibold ${
                transaction.type === 'deposit' ? 'text-green-600' : 
                'text-red-600'
              }`}>
                {transaction.type === 'deposit' ? '+' : '-'}₹{(transaction.amount * 83).toFixed(2)}
              </div>
            </div>
          )) : (
            <div className="text-center py-6 text-gray-500">
              No transactions yet
            </div>
          )}
        </div>
      </CardContent>
    </Card>
  );
}

export default WalletCard;
