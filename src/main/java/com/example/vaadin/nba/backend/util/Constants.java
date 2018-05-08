package com.example.vaadin.nba.backend.util;

public class Constants {
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String ENDPOINT_STATS = "http://stats.nba.com/stats/";
    public static final String ENDPOINT_WIDGETS = "http://stats.nba.com/js/data/widgets/";
    public static final String HEADSHOT_URL = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/%s.png";
    public static final String TEAMLOGO_URL = "https://stats.nba.com/media/img/teams/logos/%s_logo.svg";

    public static class Stats {
        public static class Key {
            public static final String PTS = "PTS";
            public static final String REB = "REB";
            public static final String AST = "AST";
            public static final String BLK = "BLK";
            public static final String STL = "STL";
            public static final String TOV = "TOV";
            public static final String FG3M = "FG3M";
            public static final String FTM = "FTM";
            public static final String NBA_FANTASY_PTS = "NBA_FANTASY_PTS";
            public static final String FANTASY_POINTS = "FANTASY_POINTS";
            public static final String FG_PCT = "FG_PCT";
            public static final String FG3_PCT = "FG3_PCT";
            public static final String FT_PCT = "FT_PCT";
            public static final String PIE = "PIE";
        }
    }

    public static class Widgets {
        public static class Uid {
            public static final String DAILY_PLAYERS ="home_daily_players";
            public static final String DAILY_TEAMS = "home_daily_teams";
            public static final String PLAYOFFS_PLAYERS = "home_daily_players_playoffs";
            public static final String PLAYOFFS_TEAMS = "home_daily_teams_playoffs";
            public static final String SEASON_PLAYERS = "season_players";
            public static final String SEASON_TEAMS = "season_teams";
        }
    }

    public static class Cache {
        public static final String DRAFT_HISTORY = "DraftHistory";
        public static final String FRANCHISE_HISTORY = "FranchiseHistory";
        public static final String COMMON_TEAM_ROSTER = "CommonTeamRoster";
        public static final String HOME_WIDGETS = "HomeWidgets";
        public static final String PLAYER_PROFILE_V2 = "PlayerProfileV2";
        public static final String COMMON_PLAYER_INFO = "CommonPlayerInfo";
    }
}
