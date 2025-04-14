
export interface UserProfile {
  email: string;
  userType: 'admin' | 'customer' | null;
  displayName?: string;
  phoneNumber?: string;
  address?: string;
  city?: string;
  state?: string;
  zipCode?: string;
  bio?: string;
  avatarUrl?: string;
}
