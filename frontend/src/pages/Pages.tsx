import { TennisCourtsPage } from "./raffle-status-page/RaffleStatusPage";
import { WorkInProgressPage } from "./notification-config-page/NotificationConfigPage";
import { PageInfo } from "../components/menu-bar/PageInfo";
import SportsTennisIcon from '@mui/icons-material/SportsTennis';
import CircleNotificationsIcon from '@mui/icons-material/CircleNotifications';

export const pages: PageInfo[] = [
  {
    title: "Raffle Status",
    path: "raffle-status",
    icon: SportsTennisIcon,
    element: TennisCourtsPage,
  },
  {
    title: "Notification Config",
    path: "notification-config",
    icon: CircleNotificationsIcon,
    element: WorkInProgressPage,
  }
]