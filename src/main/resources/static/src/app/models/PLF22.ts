export class PLF22 {
    constructor(
      public active: string = "",
      public plfDefinition: PlfDefinition = new PlfDefinition(),
      public emergency: string = ""
    ) {}
  }
  
  export class PlfDefinition {
    constructor(
      public id: string = "",
      public publicationDate: Date = new Date()
    ) {}
  }
  