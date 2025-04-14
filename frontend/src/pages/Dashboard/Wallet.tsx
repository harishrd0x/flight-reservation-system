
import { useState } from "react";
import WalletCard from "@/components/wallet/WalletCard";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { toast } from "@/components/ui/use-toast";

// Mock transactions data
const MOCK_TRANSACTIONS = [
  {
    id: "t1",
    amount: 500,
    type: "deposit" as const,
    description: "Added funds",
    date: "2025-06-02 14:30",
  },
  {
    id: "t2",
    amount: 430,
    type: "payment" as const,
    description: "Flight CJ-1245",
    date: "2025-06-01 09:15",
  },
  {
    id: "t3",
    amount: 200,
    type: "withdrawal" as const,
    description: "Withdrawal to bank",
    date: "2025-05-28 16:45",
  },
  {
    id: "t4",
    amount: 1000,
    type: "deposit" as const,
    description: "Added funds",
    date: "2025-05-15 11:20",
  },
];

export default function WalletPage() {
  const [balance, setBalance] = useState(2500.00);
  const [transactions, setTransactions] = useState(MOCK_TRANSACTIONS);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [amount, setAmount] = useState("");

  const handleAddFunds = () => {
    const parsedAmount = parseFloat(amount);
    
    if (isNaN(parsedAmount) || parsedAmount <= 0) {
      toast({
        title: "Error",
        description: "Please enter a valid amount greater than 0",
        variant: "destructive",
      });
      return;
    }
    
    // In a real app, this would make an API call to add funds
    setBalance(prev => prev + parsedAmount);
    
    // Add new transaction
    const newTransaction = {
      id: `t${Date.now()}`,
      amount: parsedAmount,
      type: "deposit" as const,
      description: "Added funds",
      date: new Date().toLocaleString('en-US', {
        year: 'numeric', 
        month: '2-digit', 
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
      }),
    };
    
    setTransactions([newTransaction, ...transactions]);
    
    setIsDialogOpen(false);
    setAmount("");
    
    toast({
      title: "Funds Added",
      description: `₹${(parsedAmount * 83).toFixed(2)} has been added to your wallet.`,
    });
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Your Wallet</h1>
        <p className="text-gray-500">
          Manage your funds and view transaction history.
        </p>
      </div>

      <WalletCard 
        balance={balance} 
        transactions={transactions} 
        onAddFunds={() => setIsDialogOpen(true)}
      />
      
      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Add Funds</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <div className="space-y-2">
              <Label htmlFor="amount">Amount</Label>
              <div className="relative">
                <span className="absolute left-3 top-1/2 -translate-y-1/2">₹</span>
                <Input
                  id="amount"
                  placeholder="0.00"
                  className="pl-8"
                  value={amount}
                  onChange={(e) => setAmount(e.target.value)}
                  type="number"
                  step="0.01"
                  min="0"
                />
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setIsDialogOpen(false)}>Cancel</Button>
            <Button onClick={handleAddFunds} className="bg-airline-blue hover:bg-airline-navy">Add Funds</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
