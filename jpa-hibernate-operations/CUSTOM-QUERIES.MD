List<Note> noteList = Arrays.asList(
				new Note("Attend office meeting", 1, true, new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2020")),
				new Note("Gym", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("14/07/2020")),
				new Note("Gym", 4, false, new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2020")),
				new Note("Gym", 2, true, new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2020")),
				new Note("Swimming", 6, false, new SimpleDateFormat("dd/MM/yyyy").parse("13/07/2020")),
				new Note("Swimming", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("13/08/2020")),
				new Note("Swimming", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("13/09/2020")),
				new Note("Temple", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("12/09/2020")),
				new Note("Party", 4, false, new SimpleDateFormat("dd/MM/yyyy").parse("11/06/2020")),
				new Note("party", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("11/07/2020")),
				new Note("party", 6, false, new SimpleDateFormat("dd/MM/yyyy").parse("11/08/2020")),
				new Note("party", 7, true, new SimpleDateFormat("dd/MM/yyyy").parse("11/09/2020")),
				new Note("office party", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2020")),
				new Note("party with friends", 5, true, new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2020")),
				new Note("Purchase Grocery", 2, true, new SimpleDateFormat("dd/MM/yyyy").parse("16/09/2020"))
				);
		noteRepository.saveAll(noteList);
