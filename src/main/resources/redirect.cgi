#!/usr/bin/perl

use strict;
use warnings;

use CGI;
use CGI::Carp qw( warningsToBrowser fatalsToBrowser );

use DBI;
use Date::Calc qw( Day_of_Week Today );
use Time::Piece;
use File::Slurp;
use Data::Dumper;
use Sort::Versions;

##### Setup stuff #####

my $cgi = CGI->new;

my $time_piece = localtime;

my $debug = $cgi->param( 'debug' ) || undef;

##### HTML stuff here #####
### Print Page ###
print $cgi->redirect( 'https://www.google.com?search=' . $cgi->param('search') );
