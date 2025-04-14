
import { useState, useEffect } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { User, Search } from "lucide-react";

// Mock wallet data
const MOCK_WALLETS = [
  {
    userId: 'user1',
    userName: 'John Doe',
    email: 'john@example.com',
    balance: 2500.00,
  },
  {
    userId: 'user2',
    userName: 'Jane Smith',
    email: 'jane@example.com',
    balance: 1200.50,
  },
  {
    userId: 'user3',
    userName: 'Alex Johnson',
    email: 'alex@example.com',
    balance: 800.75,
  },
  {
    userId: 'user4',
    userName: 'Sarah Williams',
    email: 'sarah@example.com',
    balance: 3500.25,
  }
];

interface Wallet {
  userId: string;
  userName: string;
  email: string;
  balance: number;
}

export default function WalletsPage() {
  const [searchTerm, setSearchTerm] = useState("");
  const [wallets, setWallets] = useState<Wallet[]>([]);

  // Load wallets from localStorage or use mock data
  useEffect(() => {
    try {
      const savedWallets = JSON.parse(localStorage.getItem('wallets') || '[]');
      
      if (savedWallets && savedWallets.length > 0) {
        console.log("Found wallets in localStorage:", savedWallets);
        setWallets(savedWallets);
      } else {
        // No wallets in localStorage, use mock data and save it
        localStorage.setItem('wallets', JSON.stringify(MOCK_WALLETS));
        setWallets(MOCK_WALLETS);
      }
    } catch (error) {
      console.error("Error loading wallets:", error);
      // Fallback to mock data
      setWallets(MOCK_WALLETS);
    }
  }, []);

  const filteredWallets = wallets.filter(
    (wallet) =>
      wallet.userName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      wallet.email.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Customer Wallets</h1>
        <p className="text-gray-500">
          View all customer wallet balances.
        </p>
      </div>

      <div className="relative w-full md:max-w-md">
        <Search className="absolute left-2 top-1/2 h-4 w-4 -translate-y-1/2 text-gray-500" />
        <Input
          placeholder="Search by customer name or email..."
          className="pl-8"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredWallets.length > 0 ? (
          filteredWallets.map((wallet) => (
            <Card key={wallet.userId} className="overflow-hidden hover:shadow-lg transition">
              <CardHeader className="bg-gray-50 pb-2">
                <CardTitle className="flex items-center">
                  <div className="bg-airline-blue text-white p-2 rounded-full mr-2">
                    <User className="h-4 w-4" />
                  </div>
                  {wallet.userName}
                </CardTitle>
                <p className="text-sm text-gray-500">{wallet.email}</p>
              </CardHeader>
              <CardContent className="p-6">
                <div className="flex items-center justify-between p-4 bg-airline-blue/10 rounded-lg">
                  <div>
                    <p className="text-sm text-gray-500">Balance</p>
                    <h3 className="text-2xl font-bold">₹{(wallet.balance * 83).toFixed(2)}</h3>
                  </div>
                </div>
              </CardContent>
            </Card>
          ))
        ) : (
          <div className="col-span-full text-center py-12">
            <User className="h-12 w-12 mx-auto text-gray-300" />
            <h3 className="mt-4 text-lg font-medium">No wallets found</h3>
            <p className="mt-1 text-gray-500">Try adjusting your search terms</p>
          </div>
        )}
      </div>
    </div>
  );
}
